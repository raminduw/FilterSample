package com.ramindu.weeraman.filter.sample;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ramindu.weeraman.filter.sample.data.Repository;
import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;
import com.ramindu.weeraman.filter.sample.ui.LoadingStatus;
import com.ramindu.weeraman.filter.sample.ui.viewmodel.UserListViewModel;
import com.ramindu.weeraman.filter.sample.utils.SchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UserViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    UserItem loggedUser;
    private UserListViewModel viewModel;
    private Repository repository;
    private CompositeDisposable compositeDisposable;
    private SchedulerProvider schedulerProvider;
    private FilterOptionMap filterOptionMap;

    @Before
    public void before() throws Exception {
        repository = mock(Repository.class);
        schedulerProvider = mock(SchedulerProvider.class);
        filterOptionMap = mock(FilterOptionMap.class);
        loggedUser = mock(UserItem.class);
        compositeDisposable = mock(CompositeDisposable.class);
        viewModel = new UserListViewModel(repository, filterOptionMap, compositeDisposable,
                schedulerProvider);
    }


    @Test
    public void testRetrieveUserListFromRemoteSuccess() {

        List<UserItem> userList = new ArrayList<>();

        when(repository.getData())
                .thenReturn(Observable.just(userList));

        when(repository.isDataAvailable())
                .thenReturn(false);

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromRemote();
        assertEquals(LoadingStatus.SUCCESS, viewModel.getStatusObserver().getValue());
    }


    @Test
    public void testRetrieveUserListFromRemoteNotInvoke() {

        List<UserItem> userList = new ArrayList<>();

        when(repository.getData())
                .thenReturn(Observable.just(userList));

        when(repository.isDataAvailable())
                .thenReturn(true);

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());
        viewModel.getDataFromRemote();

        InOrder inOrder = Mockito.inOrder(repository);
        inOrder.verify(repository, times(0)).setAllUserList(userList);

    }

    @Test
    public void testUserListCountWhenRetrieveUserListFromRemoteSuccess() {

        List<UserItem> userList = new ArrayList<>();
        userList.add(new UserItem());
        userList.add(new UserItem());
        userList.add(new UserItem());

        when(repository.isDataAvailable())
                .thenReturn(false);
        when(repository.getData())
                .thenReturn(Observable.just(userList));

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromRemote();
        assertEquals(3, viewModel.getUserList().getValue().size());
    }

    @Test
    public void testDBSaveWhenRetrieveUserListFromRemoteSuccess() {

        List<UserItem> userList = new ArrayList<>();

        when(repository.isDataAvailable())
                .thenReturn(false);
        when(repository.getData())
                .thenReturn(Observable.just(userList));

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromRemote();

        InOrder inOrder = Mockito.inOrder(repository);
        inOrder.verify(repository, times(1)).setAllUserList(userList);
    }


    @Test
    public void testRetrieveUserListFromRemoteFail() {
        Exception exception = new Exception();
        when(repository.isDataAvailable())
                .thenReturn(false);
        when(repository.getData())
                .thenReturn(Observable.<List<UserItem>>error(exception));

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromRemote();
        assertEquals(LoadingStatus.FAIL, viewModel.getStatusObserver().getValue());
    }


    @Test
    public void testRetrieveUserListFromLocalSuccess() {

        List<UserItem> userList = new ArrayList<>();
        UserItem userItem = new UserItem();
        userList.add(userItem);

        when(repository.getLocalUserList())
                .thenReturn(Observable.fromIterable(userList));

        when(repository.isApplicableUser(userItem,loggedUser, filterOptionMap))
                .thenReturn(true);

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromLocalWithFilters();
        assertEquals(LoadingStatus.SUCCESS, viewModel.getStatusObserver().getValue());
    }

    @Test
    public void testRetrieveUserListFromLocalFail() {
        Exception exception = new Exception();

        when(repository.getLocalUserList())
                .thenReturn(Observable.<UserItem>error(exception));

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromLocalWithFilters();
        assertEquals(LoadingStatus.FAIL, viewModel.getStatusObserver().getValue());
    }

    @Test
    public void testRetrieveUserListFromLocalSuccessWithFilters() {

        List<UserItem> userList = new ArrayList<>();
        UserItem userItem1 = new UserItem();
        userItem1.setDisplayName("MARK");

        UserItem userItem2 = new UserItem();
        userItem2.setDisplayName("ANNE");

        userList.add(userItem1);
        userList.add(userItem2);


        when(repository.getLocalUserList())
                .thenReturn(Observable.fromIterable(userList));

        when(repository. getLoggedUser())
                .thenReturn(loggedUser);

        when(repository.isApplicableUser(userItem1,loggedUser, filterOptionMap))
                .thenReturn(true);

        when(repository.isApplicableUser(userItem2,loggedUser, filterOptionMap))
                .thenReturn(false);

        when(schedulerProvider.getBackgroundScheduler())
                .thenReturn(Schedulers.trampoline());

        when(schedulerProvider.getMainScheduler())
                .thenReturn(Schedulers.trampoline());

        viewModel.getDataFromLocalWithFilters();

        assertEquals(LoadingStatus.SUCCESS, viewModel.getStatusObserver().getValue());
        assertEquals(1, viewModel.getUserList().getValue().size());
        assertEquals("MARK", viewModel.getUserList().getValue().get(0).getDisplayName());
    }


}

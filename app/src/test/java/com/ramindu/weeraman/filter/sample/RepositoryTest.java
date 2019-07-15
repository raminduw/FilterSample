package com.ramindu.weeraman.filter.sample;

import com.ramindu.weeraman.filter.sample.data.Repository;
import com.ramindu.weeraman.filter.sample.data.model.UserItem;
import com.ramindu.weeraman.filter.sample.data.remote.UserApi;
import com.ramindu.weeraman.filter.sample.filter.FilterManager;
import com.ramindu.weeraman.filter.sample.filter.FilterOptionMap;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTest {
    UserApi userApi;
    FilterOptionMap filterOptions;
    FilterManager filterManager;
    UserItem loggedUser;
    UserItem userItem;
    private Repository repository;

    @Before
    public void before() throws Exception {
        userApi = mock(UserApi.class);
        filterManager = mock(FilterManager.class);
        filterOptions = mock(FilterOptionMap.class);
        loggedUser = mock(UserItem.class);
        userItem = mock(UserItem.class);
        repository = new Repository(userApi, filterManager);

    }

    @Test
    public void testSetUserListWithEmptyList() {
        List<UserItem> userList = new ArrayList<>();
        repository.setAllUserList(userList);
        assertFalse(repository.isDataAvailable());
    }

    @Test
    public void testSetUserListWithNullList() {

        List<UserItem> userList = null;
        repository.setAllUserList(userList);
        assertFalse(repository.isDataAvailable());
    }

    @Test
    public void testSetUserListWithNotEmptyList() {

        List<UserItem> userList = new ArrayList<>();
        userList.add(new UserItem());
        repository.setAllUserList(userList);
        assertTrue(repository.isDataAvailable());
    }

    @Test
    public void testClearData() {

        List<UserItem> userList = new ArrayList<>();
        userList.add(new UserItem());
        repository.setAllUserList(userList);
        repository.clearData();
        assertFalse(repository.isDataAvailable());
    }

    @Test
    public void testIsApplicableUserWithNotApplicableUser() {
        when(filterManager.isApplicableUser(userItem, loggedUser ,filterOptions))
                .thenReturn(false);
        assertFalse(repository.isApplicableUser(userItem, loggedUser ,filterOptions));
    }

    @Test
    public void testIsApplicableUserWithApplicableUser() {
        when(filterManager.isApplicableUser(userItem, loggedUser ,filterOptions))
                .thenReturn(true);
        assertTrue(repository.isApplicableUser(userItem, loggedUser ,filterOptions));
    }

}

# FilterSample
This is a sample application to demonstrate android MVVM architecture.

---
### Libraries Used:
a) AndroidRx2</br> 
b) Dagger2</br>
c) Retrofit2</br>
d) Java 8</br>
e) LiveData/ViewModel</br>
f) Mockito</br>
g) Espresso</br>
h) https://github.com/syedowaisali/crystal-range-seekbar</br>

Strictly followed MVVM architecture. This helps in proper deep Unit testing and code modularity.</br>

Actual URL -  https://testapi-b0e59.firebaseio.com/matches.json</br>
Testing URL -  https://testmockapi-d23bc.firebaseio.com/matches.json</br>

### Assumptions:
Used logged user's Locations as below.</br>
Lat :53.801277</br>
Lon :-1.548567 

### Filtering:
Web API does not support any filtering and client side filtering applied.(Hosted on Firebase as a json).
Applciation initially downlaod all the users from API and filter locallay when filter applies.
If application screen oriantation change, app will not call the API again to retrieve data.

### Notes:
Application support both Landsacape and Portrait modes.</br>
Supportmultple screen sizes.

### Testing:
Repository and View model unit tested using JUnit and Mockito.</br>
Activity UI tested using Espresso.</br>

### Screens:
![list_view](https://user-images.githubusercontent.com/5441853/61299244-4b6fb300-a812-11e9-8bc7-83cca21815a0.png)
</br></br>
![filter_view](https://user-images.githubusercontent.com/5441853/61299249-4d397680-a812-11e9-81ae-d4d6a2ce3098.png)

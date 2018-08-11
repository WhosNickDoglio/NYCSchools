# NYC Schools

Small android application written as a coding challenge for an interview for an Android Developer position. 
The master branch contains the work finished in the given amount of time (48 Hours) with the [Kotlin branch](https://github.com/WhosNickDoglio/NYCSchools/tree/kotlin) contains a version 
rewritten with improvements that were not achievable in the given time frame.

<img src="https://i.imgur.com/n8aEgkj.png" width="250" height="450"> <img src="https://i.imgur.com/wwqeaEe.png" width="250" height="450">

## Presentation Pattern
<img src="https://i0.wp.com/www.tinmegali.com/wp-content/uploads/2016/02/MVP.png?resize=800%2C220&ssl=1">

This project follows the Model-View-Presenter (MVP) presentation pattern. This breaks down to:

- Model: A data management layer that handles any business logic

- View: Responsible for displaying data from the Presenter and notifying the Presenter of any user input

- Presenter: A mediator layer between the model and the view that connects the two, it contains all the presentation logic,
 like updating the model and reacting to input from the view 


Each screen has a `Contract` that outlines interfaces for the relationship between the `View` and `Presenter`, each corresponding 
`View` or `Presenter` implements their interface and uses it to communicate between each other. 


## Libraries 
- [Butterknife](https://jakewharton.github.io/butterknife/) for resource binding
- [Dagger](https://google.github.io/dagger/) for dependency injection
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) for 
asynchronous network calls and threading
- [Retrofit](https://square.github.io/retrofit/) for network calls
- [Mockito](https://site.mockito.org/) for testing  

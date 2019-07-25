# autoDisposable
DIY Auto Disposable for RxJava with Lifecycle Architecture Component
Demo for my tech-blog https://medium.com/mindorks/autodisposable-for-rxjava-with-lifecycle-architecture-component-23dfcfa83a2

## Usage:
### Init your AutoDisposable:
```
   private val autoDisposable = AutoDisposable()
```

### Bind your AutoDisposable into a lifecycle

```
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        autoDisposable.bindTo(this.lifecycle) //support Fragment or Activity
        //....
    }
```
### Add your Disposable to the auto disposable, that's it
```
    var disposable = observable.subscribeWith(observer) 
    autoDisposable.add(disposable)
```

### Even cooler, if you use the `addTo` extension function of the AutoDisposable

```
   Observable.just("Hello")
                    //Do some thing
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(
                       { Log.i(TAG, "onNext: $it") },
                       { Log.e(TAG, "Error: ${it.message}") }
                   )
                   .addTo(autoDisposable) // <-- magic here
```

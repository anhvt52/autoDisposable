# autoDisposable
DIY Auto Disposable for RxJava with Lifecycle Architecture Component

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
    var disposable = observable.subscribe(observer) 
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
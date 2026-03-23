### First Case

 - Exception `ex` in `B` propagates uncaught all the way to `Thread.run()`
 - Exception `ex` handled by `Thread.UncaughtExceptionHandler`, which, by default, prints error into stderr
 - Thread `B` termnates
 - Thread `A` successfuly joins thread `B`, because `B` is terminated. No exception is propagated.
 - Thread `A` termnates succesfully. 

### Second case

 - All of the above, except last step.
 - Thread `C` joins `B`. `B` is already terminated, so no need to wait for anything here (and no exception again).

### Third case

 - As before, now ending with `D` joining `A`, meaning `D` waits for `A`'s termination.
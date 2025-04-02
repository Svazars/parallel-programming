package org.nsu.syspro.parprog.solution;

import org.nsu.syspro.parprog.UserThread;
import org.nsu.syspro.parprog.external.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * {@link SolutionThread} extends {@link UserThread} and implements the logic of optimized execution of methods,
 * using caching and multithreaded compilation to improve performance.
 * {@link SolutionThread} supports advanced Just-In-Time compilation techniques:
 * <p>
 *     <ul>
 *     1) Level 1 JIT is very fast, but produces suboptimal machine code;
 *     <br>
 *     2) Level 2 JIT is the best, but takes a long time to compile.
 *     </ul>
 * </p>
 * <br>
 * This class uses two {@link ConcurrentHashMap} classes to store {@link CompiledMethod}s and their compilation level,
 * as well as an {@link ExecutorService} for asynchronous compilation of Level 2 JIT methods.
 */
public class SolutionThread extends UserThread {

    /**
     * Cache of compiled methods, where the key is {@link MethodID} of the method,
     * and the value is {@link CompiledMethod} compiled version of the method.
     */
    private final ConcurrentHashMap<MethodID, CompiledMethod> cache = new ConcurrentHashMap<>();

    /**
     * {@link ConcurrentHashMap}, where the key is {@link MethodID} of the method, and the value is {@link Long}
     * representing the compilation level (for example, 1 or 2).
     * <br>
     * If the method has not yet been compiled, then its value will not be in the list of keys of this map.
     */
    private final ConcurrentHashMap<MethodID, Long> methodLevelCompilation = new ConcurrentHashMap<>();

    /**
     * {@link ExecutorService} for asynchronous compilation of 2nd-level JIT methods
     * with a limited number of threads specified by the {@code compilationThreadBound} variable.
     */
    private ExecutorService executorService;

    public SolutionThread(int compilationThreadBound, ExecutionEngine exec, CompilationEngine compiler, Runnable r) {
        super(compilationThreadBound, exec, compiler, r);
        executorService = Executors.newFixedThreadPool(compilationThreadBound);
    }

    /**
     * Method execution function.
     * <p>
     * If the method has already been compiled (present in {@code methodLevelCompilation}), then there are two options:
     *     <ul>
     *     1) the method is already compiled with level 2, then it is executed from the cache;
     *     <br>
     *     2) the method is compiled with level 1, then it needs to be recompiled with level 2 JIT and executed using the new cache.
     *     </ul>
     *     <br>
     * If not, then compile the method with level 1 JIT and execute it using the new cache.
     * </p>
     */
    @Override
    public ExecutionResult executeMethod(MethodID id) {

        final long methodID = id.id();

        Long level = methodLevelCompilation.get(id);

        if (level != null) {
            CompiledMethod cacheMethod = cache.get(id);
            if (level == 2L) {
                return exec.execute(cacheMethod);
            } else {
                executorService.submit(() -> {
                    CompiledMethod l2CompiledMethod = compiler.compile_l2(id);
                    addNewCache(id, l2CompiledMethod, 2L);
                });
                cacheMethod = cache.get(id);
                return exec.execute(cacheMethod);
            }
        }

        CompiledMethod l1CompiledMethod = compiler.compile_l1(id);
        addNewCache(id, l1CompiledMethod, 1L);
        return exec.execute(l1CompiledMethod);
    }

    /**
     * Adds the compiled method to the cache and updates the compilation level.
     * @param methodID {@link MethodID} of the method.
     * @param compiledMethod {@link CompiledMethod} compiled version of the method.
     * @param level Compilation level.
     */
    private void addNewCache(MethodID methodID, CompiledMethod compiledMethod, long level){
        cache.put(methodID, compiledMethod);
        methodLevelCompilation.put(methodID, level);
    }
}
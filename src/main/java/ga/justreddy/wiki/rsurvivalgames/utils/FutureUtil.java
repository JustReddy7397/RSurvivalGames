package ga.justreddy.wiki.rsurvivalgames.utils;

import ga.justreddy.wiki.rsurvivalgames.RSurvivalGames;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

public class FutureUtil {

    public static <T> CompletableFuture<T> future(Callable<T> supplier) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        return supplier.call();
                    } catch (Exception e) {
                        if (e instanceof RuntimeException) {
                            throw (RuntimeException) e;
                        }
                        throw new CompletionException(e);
                    }
                }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().scheduleSyncDelayedTask(RSurvivalGames.getPlugin(RSurvivalGames.class), r)
        );
    }

    public static <T> CompletableFuture<T> futureAsync(Callable<T> supplier) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        return supplier.call();
                    } catch (Exception e) {
                        if (e instanceof RuntimeException) {
                            throw (RuntimeException) e;
                        }
                        throw new CompletionException(e);
                    }
                }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().runTaskAsynchronously(RSurvivalGames.getPlugin(RSurvivalGames.class), r)
        );
    }

    public static CompletableFuture<Void> future(Runnable runnable) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().scheduleSyncDelayedTask(RSurvivalGames.getPlugin(RSurvivalGames.class), r));
    }

    public static CompletableFuture<Void> futureAsync(Runnable runnable) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().runTaskAsynchronously(RSurvivalGames.getPlugin(RSurvivalGames.class), r));
    }


    public static <T> CompletableFuture<T> delayedAsync(Callable<T> supplier, int ticks) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.call();
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().runTaskLaterAsynchronously(RSurvivalGames.getPlugin(RSurvivalGames.class), r, ticks));
    }

    public static CompletableFuture<Void> delayedAsync(Runnable runnable, int ticks) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw (RuntimeException) e;
            }
        }, r -> RSurvivalGames.getPlugin(RSurvivalGames.class).getServer().getScheduler().runTaskLaterAsynchronously(RSurvivalGames.getPlugin(RSurvivalGames.class), r, ticks));
    }


}

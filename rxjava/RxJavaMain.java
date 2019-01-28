package ru.ileanpro.riatworker.rxjava;


import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class RxJavaMain {
    public static void main(String[] args) {

        //простой вывод
        Observable.just("Hello", "Mira", " mira", "\n").subscribe(System.out::println);
        //just null передавать нельзя
        Observable.just("Hello", "null", " mira", "\n").subscribe(System.out::println);
        //map/trim/маленькие буквы
        Observable.just("Kama", "Ok", "Volga", " mo\n").map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.trim().toLowerCase();
            }
        }).subscribe(System.out::println, System.out::println, () -> System.out.println("onComplete"));
        //reduce объединяеть слова/ map дедает маленькие буквы
        Observable.just("A ", "B", " C ", "\n").reduce(new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s.toLowerCase();
            }
        }).subscribe(System.out::println);

        //fromCallable вернуть какое то значения
        Observable<ArrayList<String>> arrayListObservable = Observable.fromCallable(new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() throws Exception {
                ArrayList<String> list = new ArrayList<>();
                list.add("book");
                list.add("apple");
                list.add("milk");
                return list;
            }
        });
        arrayListObservable.subscribe(System.out::println);

        Observable.range(5, 4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("от какого числа начаит(5), и сколько цифр показать(4) " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Observable.just("a b c d").switchMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.fromArray(s.split(" "));
            }
        }).subscribe(System.out::println);

        System.out.println("\n");
        Observable.range(4, 5).map(integer -> {
            Thread.sleep(2000);
            System.out.println("" + Thread.currentThread().getName());
            return integer.toString();
        }).subscribeOn(Schedulers.computation())
                .subscribe(t -> System.out.println("" + t));
//        subscribeOn(Schedulers.io())

        Observable.range(4, 5)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        return Observable.just(integer).map(number -> {
                            Thread.sleep(2000);
                            System.out.println("" + Thread.currentThread().getName());
                            return number.toString();
                        }).subscribeOn(Schedulers.io());
                    }
                })
//                .subscribeOn(Schedulers.computation())
                .subscribe(t -> System.out.println("" + t));

        ArrayList<String> listId = new ArrayList<>();
        listId.add("1");
        listId.add("2");
        listId.add("3");
//        ArrayList<String> listData = new ArrayList<>();
//        listData = null;
//
//        ArrayList<String> finalListData = listData;
//        Observable.just(listId)
//                .flatMap((Function<ArrayList<String>, ObservableSource<?>>)
//                        Observable::just)
//                .map(t -> t.toString())
//                .map(t -> {
//                    t = null;
//                    t.toString();
//                    if (t == null) {
//                        return "null";
//                    }
//                    return "text";
//                })
//                .filter(new Predicate<Object>() {
//                    @Override
//                    public boolean test(Object text) throws Exception {
//                        if (text.equals("null")) {
//                            return false;
//                        }
//                        return true;
//                    }
//                })
//                .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<List<String>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onSuccess(List<String> strings) {
//                        Log.e("my", "list " + strings);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("my", "Throwable " + e.getMessage());
//                    }
//                });


        Observable.just(listId)
                .forEach(strings -> System.out.println(strings));

        System.out.println("\n______________PublishSubject");
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("Name");
        subject.subscribe(t -> System.out.println(t));
        subject.onNext("two");
        System.out.println("\n______________ReplaySubject");
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("kia");
        replaySubject.subscribe(t -> System.out.println(t));
        replaySubject.onNext("rio");
        System.out.println("\n______________ReplaySubject");
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext("1");
        behaviorSubject.onNext("2");
        behaviorSubject.onNext("3");
        behaviorSubject.subscribe(t -> System.out.println(t));
        behaviorSubject.onNext("4");
        behaviorSubject.onNext("5");


    }
}

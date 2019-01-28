package ru.ileanpro.riatworker.rxjava;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEventBus {

    private static RxEventBus instance;

    private PublishSubject<String> subject = PublishSubject.create();
    private PublishSubject<String> subjectTwo = PublishSubject.create();

    public static RxEventBus instanceOf() {
        if (instance == null) {
            instance = new RxEventBus();
        }
        return instance;
    }

    /**
     * Pass any event down to event listeners.
     */
    public void setString(String string) {
        subject.onNext(string);
    }

    public void setMessage(String string) {
        subjectTwo.onNext(string);
    }

    /**
     * Subscribe to this Observable. On event, do something
     * e.g. replace a fragment
     */
    public Observable<String> getEvents() {
        return subject;
    }

    public Observable<String> getEventsOne() {
        return subjectTwo;
    }
}
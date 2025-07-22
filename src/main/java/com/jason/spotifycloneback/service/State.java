package com.jason.spotifycloneback.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class State<T, V> {
    private StatusNotification status;
    private T value;
    private V error;

    public static <T,V> State<T,V> onSuccess(T value) {
        return new State<>(StatusNotification.OK,  value, null);
    }

    public static <T,V> State<T,V> onError(V error) {
        return new State<>(StatusNotification.ERROR,  null, error);
    }




}

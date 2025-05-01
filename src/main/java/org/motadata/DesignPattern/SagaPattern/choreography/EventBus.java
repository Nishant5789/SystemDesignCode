package org.motadata.DesignPattern.SagaPattern.choreography;

import java.util.*;

class EventBus
{
    private static final Map<Class<?>, List<Consumer<Object>>> listeners = new HashMap<>();

    public static <T> void subscribe(Class<T> eventType, Consumer<T> listener)
    {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>())
        .add((Consumer<Object>) listener);
    }

    public static void publish(Object event)
    {
        List<Consumer<Object>> subs = listeners.get(event.getClass());
        if (subs!=null)
        {
            for (Consumer<Object> listener : subs)
            {
                listener.accept(event);
            }
        }
    }

    interface Consumer<T>
    {
        void accept(T t);
    }
}
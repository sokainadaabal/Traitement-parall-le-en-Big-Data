package ma.enset.comptecqrses.commonapi.events;

import lombok.Getter;

@Getter
public abstract class BaseEvent<T> {
    private T id; //  c'est un simple objet

    public BaseEvent(T id) {
        this.id = id;
    }
}

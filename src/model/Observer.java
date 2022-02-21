package model;

/**
 * Observer interface for updates to be made to games
 * @param <Subject> Type of object interface is observing.
 * @param <ClientData> Data model can send to observers (Can be null)
 */
public interface Observer<Subject, ClientData> {
    void update(Subject subject, ClientData data);
}

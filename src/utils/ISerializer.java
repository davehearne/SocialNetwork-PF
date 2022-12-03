package utils;

public interface ISerializer {
    void save() throws Exception;
    void load() throws Exception;
    String fileName();
}

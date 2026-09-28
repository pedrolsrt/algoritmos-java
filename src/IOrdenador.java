import java.util.Comparator;

public interface IOrdenador<T> {
    void ordenar(T[] vetor, Comparator<T> comparador);
}

import java.util.Comparator;

public class Bolha<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        for (int fim = vetor.length - 1; fim > 0; fim--) {
            boolean trocou = false;

            for (int i = 0; i < fim; i++) {
                if (comparador.compare(vetor[i], vetor[i + 1]) > 0) {
                    T temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocou = true;
                }
            }

            if (!trocou) {
                break;
            }
        }
    }
}

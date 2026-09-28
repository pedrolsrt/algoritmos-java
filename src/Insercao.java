import java.util.Comparator;

public class Insercao<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        for (int i = 1; i < vetor.length; i++) {
            T chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && comparador.compare(vetor[j], chave) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }

            vetor[j + 1] = chave;
        }
    }
}

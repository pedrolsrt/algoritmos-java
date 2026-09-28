import java.util.Comparator;

public class Selecao<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {
                if (comparador.compare(vetor[j], vetor[menor]) < 0) {
                    menor = j;
                }
            }

            T temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }
}

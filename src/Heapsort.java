import java.util.Comparator;

public class Heapsort<T> implements IOrdenador<T> {

    @Override
    public void ordenar(T[] vetor, Comparator<T> comparador) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i, comparador);
        }

        for (int fim = n - 1; fim > 0; fim--) {
            T temp = vetor[0];
            vetor[0] = vetor[fim];
            vetor[fim] = temp;

            heapify(vetor, fim, 0, comparador);
        }
    }

    private void heapify(T[] vetor, int tamanho, int raiz, Comparator<T> comparador) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho && comparador.compare(vetor[esquerda], vetor[maior]) > 0) {
            maior = esquerda;
        }

        if (direita < tamanho && comparador.compare(vetor[direita], vetor[maior]) > 0) {
            maior = direita;
        }

        if (maior != raiz) {
            T temp = vetor[raiz];
            vetor[raiz] = vetor[maior];
            vetor[maior] = temp;

            heapify(vetor, tamanho, maior, comparador);
        }
    }
}

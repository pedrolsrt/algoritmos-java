import java.util.Comparator;

// EXEMPLO COMPLETO 1 - PRODUTOS
// Modelo pronto para adaptar na prova.
// Se o enunciado trocar Produto por Aluno, Livro, Pessoa etc.,
// basta trocar o nome da classe e os atributos.

interface IOrdenador<T> {
    void ordenar(T[] vetor, Comparator<T> comparador);
}

class Selecao<T> implements IOrdenador<T> {
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

class Produto {
    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$ " + preco;
    }
}

public class ExemploCompletoProduto {
    public static void main(String[] args) {

        Produto[] produtos = {
            new Produto(3, "Mouse", 89.90),
            new Produto(1, "Teclado", 149.90),
            new Produto(4, "Monitor", 999.90),
            new Produto(2, "Headset", 199.90)
        };

        // Critérios de ordenação prontos.
        Comparator<Produto> porCodigo =
            Comparator.comparingInt(Produto::getCodigo);

        Comparator<Produto> porNome =
            Comparator.comparing(Produto::getNome);

        Comparator<Produto> porPreco =
            Comparator.comparingDouble(Produto::getPreco);

        // Troque Selecao por Bolha, Insercao, Mergesort,
        // Heapsort ou Quicksort se o enunciado pedir outro algoritmo.
        IOrdenador<Produto> ordenador = new Selecao<>();

        // Troque porCodigo, porNome ou porPreco conforme o enunciado.
        ordenador.ordenar(produtos, porPreco);

        System.out.println("Produtos ordenados:");

        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}

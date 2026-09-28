import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Produto[] produtos = {
            new Produto(3, "Mouse", 89.90),
            new Produto(1, "Teclado", 149.90),
            new Produto(4, "Monitor", 999.90),
            new Produto(2, "Headset", 199.90)
        };

        Comparator<Produto> porCodigo =
            Comparator.comparingInt(Produto::getCodigo);

        Comparator<Produto> porNome =
            Comparator.comparing(Produto::getNome);

        Comparator<Produto> porPreco =
            Comparator.comparingDouble(Produto::getPreco);

        // Troque somente esta linha para mudar o algoritmo.
        IOrdenador<Produto> ordenador = new Quicksort<>();

        // Troque o comparador para ordenar por codigo, nome ou preco.
        ordenador.ordenar(produtos, porPreco);

        for (Produto produto : produtos) {
            System.out.println(produto);
        }

        // Exemplos de troca rapida:
        // ordenador = new Selecao<>();
        // ordenador = new Bolha<>();
        // ordenador = new Insercao<>();
        // ordenador = new Mergesort<>();
        // ordenador = new Heapsort<>();
        // ordenador = new Quicksort<>();
        // ordenador.ordenar(produtos, porNome.reversed());
    }
}

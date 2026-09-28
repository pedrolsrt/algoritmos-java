import java.util.Arrays;

/*
 * EXEMPLO COMPLETO COM COMPARABLE
 *
 * Ideia:
 * - A própria classe Aluno define sua ordem natural.
 * - Neste exemplo, a ordem natural será por nota.
 * - Assim, o algoritmo de ordenação não precisa receber Comparator.
 *
 * Na prova, você pode adaptar:
 * Aluno -> Produto / Livro / Pessoa / Funcionario
 * nota -> preco / salario / ano / idade
 */

public class ExemploCompletoComparable {

    public static void main(String[] args) {

        Aluno[] alunos = {
            new Aluno(103, "Pedro", 8.5),
            new Aluno(101, "Ana", 9.2),
            new Aluno(104, "Carlos", 6.8),
            new Aluno(102, "Bruna", 7.9)
        };

        System.out.println("ANTES:");
        imprimir(alunos);

        IOrdenadorComparable<Aluno> ordenador =
            new SelecaoComparable<>();

        ordenador.ordenar(alunos);

        System.out.println("\nDEPOIS - ORDEM NATURAL POR NOTA:");
        imprimir(alunos);
    }

    private static void imprimir(Aluno[] alunos) {
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}


/*
 * Interface polimórfica.
 *
 * T precisa implementar Comparable<T>.
 * Isso garante que os objetos sabem se comparar entre si.
 */
interface IOrdenadorComparable<T extends Comparable<T>> {

    void ordenar(T[] vetor);

}


/*
 * Selection Sort genérico usando Comparable.
 *
 * A diferença principal para a versão com Comparator é esta:
 *
 * vetor[j].compareTo(vetor[menor])
 *
 * Em vez de receber um Comparator externo, a comparação
 * fica definida dentro da própria classe do objeto.
 */
class SelecaoComparable<T extends Comparable<T>>
        implements IOrdenadorComparable<T> {

    @Override
    public void ordenar(T[] vetor) {

        for (int i = 0; i < vetor.length - 1; i++) {

            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {

                if (vetor[j].compareTo(vetor[menor]) < 0) {
                    menor = j;
                }
            }

            T temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }
}


/*
 * Classe de exemplo.
 *
 * A classe implementa Comparable<Aluno>.
 * O método compareTo define a ORDEM NATURAL do objeto.
 *
 * Aqui:
 * menor nota vem primeiro.
 */
class Aluno implements Comparable<Aluno> {

    private int matricula;
    private String nome;
    private double nota;

    public Aluno(int matricula, String nome, double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    /*
     * ORDEM NATURAL:
     * nota crescente.
     *
     * Se quiser mudar para matrícula:
     * return Integer.compare(this.matricula, outro.matricula);
     *
     * Se quiser mudar para nome:
     * return this.nome.compareToIgnoreCase(outro.nome);
     *
     * Se quiser ordem decrescente por nota:
     * return Double.compare(outro.nota, this.nota);
     */
    @Override
    public int compareTo(Aluno outro) {
        return Double.compare(this.nota, outro.nota);
    }

    @Override
    public String toString() {
        return matricula
            + " - "
            + nome
            + " - Nota: "
            + nota;
    }
}

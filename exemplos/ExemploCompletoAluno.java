import java.util.Comparator;

// EXEMPLO COMPLETO 2 - ALUNOS
// Este exemplo mostra uma variação muito comum de prova:
// criar objetos e ordenar por atributos diferentes.

interface MetodoOrdenacao<T> {
    void ordenar(T[] vetor, Comparator<T> comparador);
}

class Insercao<T> implements MetodoOrdenacao<T> {
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

class Aluno {
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

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " - Nota: " + nota;
    }
}

public class ExemploCompletoAluno {
    public static void main(String[] args) {

        Aluno[] alunos = {
            new Aluno(103, "Carlos", 7.5),
            new Aluno(101, "Ana", 9.2),
            new Aluno(104, "Bruno", 6.8),
            new Aluno(102, "Daniel", 8.4)
        };

        Comparator<Aluno> porMatricula =
            Comparator.comparingInt(Aluno::getMatricula);

        Comparator<Aluno> porNome =
            Comparator.comparing(Aluno::getNome);

        Comparator<Aluno> porNota =
            Comparator.comparingDouble(Aluno::getNota);

        MetodoOrdenacao<Aluno> ordenador = new Insercao<>();

        // Exemplo: ordenar por nota crescente.
        ordenador.ordenar(alunos, porNota);

        System.out.println("Alunos ordenados:");

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}

//aluno: Lucas Tourinho Mamede
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Automaton {
    // Definição dos estados
    private enum State {
        R, S, T, U
    }

    // Função de transição
    private static Set<State> transition(Set<State> currentStates, char input) {
        Set<State> nextStates = new HashSet<>();

        for (State state : currentStates) {
            switch (state) {
                case R:
                    if (input == 'a') {
                        nextStates.add(State.S);
                    } else if (input == 'b') {
                        nextStates.add(State.U);
                    }
                    break;
                case S:
                    if (input == 'a') {
                        nextStates.add(State.R);
                    } else if (input == 'b') {
                        nextStates.add(State.T);
                    }
                    break;
                case T:
                    if (input == 'a') {
                        nextStates.add(State.U);
                    } else if (input == 'b') {
                        nextStates.add(State.S);
                    }
                    break;
                case U:
                    if (input == 'a') {
                        nextStates.add(State.T);
                    } else if (input == 'b') {
                        nextStates.add(State.R);
                    }
                    break;
            }
        }
        return nextStates;
    }

    // Função que verifica se a palavra leva ao estado de aceitação e retorna o caminho
    public static String getPath(String word) {
        Set<State> currentStates = new HashSet<>();
        currentStates.add(State.R);

        List<Set<State>> path = new ArrayList<>();
        path.add(new HashSet<>(currentStates)); // Adiciona o estado inicial

        for (char c : word.toCharArray()) {
            currentStates = transition(currentStates, c);
            path.add(new HashSet<>(currentStates)); // Adiciona os estados após cada transição
        }

        // Montar o caminho como uma string
        StringBuilder pathString = new StringBuilder("w => ");
        for (Set<State> states : path) {
            pathString.append(states.toString()).append(" -> ");
        }
        pathString.delete(pathString.length() - 4, pathString.length()); // Remove a última seta " -> "

        return pathString.toString();
    }

    // Função que verifica se a palavra é aceita
    public static boolean isAccepted(String word) {
        Set<State> currentStates = new HashSet<>();
        currentStates.add(State.R);

        for (char c : word.toCharArray()) {
            currentStates = transition(currentStates, c);
        }

        // Verifica se algum dos estados finais é R
        return currentStates.contains(State.R);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word composed of 'a' and 'b': ");
        String word = scanner.nextLine();

        String path = getPath(word);
        boolean accepted = isAccepted(word);

        System.out.println("Word: " + word + " -> " + (accepted ? "Accepted" : "Rejected") + " | Path: " + path);

        scanner.close();
    }
}

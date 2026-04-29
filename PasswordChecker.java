import java.util.Scanner;

import static java.lang.Math.min;

public class PasswordChecker {
    private int minLength;
    private boolean wasMinLengthValueSet = false;
    private int maxRepeats;
    private boolean wasMaxRepeatsValueSet = false;
    public void setMinLength(int length){
        if(length < 0){
            throw new IllegalArgumentException("В параметре надо указать неотрицательную минимальную длину, но было указано: " + length);
        }
        else {
            minLength = length;
            wasMinLengthValueSet = true;
        }
    }
    public void setMaxRepeats(int repeats){
        if(repeats <= 0){
            throw new IllegalArgumentException("В параметре надо указать минимальную длину, большую нуля, но было указано: " + repeats);
        }
        else {
            maxRepeats = repeats;
            wasMaxRepeatsValueSet = true;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите мин. длину пароля: ");
        PasswordChecker passwordChecker = new PasswordChecker();
        passwordChecker.setMinLength(scanner.nextInt());
        System.out.println();
        System.out.print("Введите макс. допустимое количество повторений символа подряд: ");
        passwordChecker.setMaxRepeats(scanner.nextInt());
        System.out.println();
        String stopWord = "end";
        String enteredPassword = "";
//        do {
//            // код
//        } while (условие);
        do {
            System.out.print("Введите пароль или end: ");
            enteredPassword = scanner.nextLine();
            System.out.println();

            if(passwordChecker.verify(enteredPassword)){
                System.out.println("Подходит!");
            }
            else{
                System.out.println("Не подходит!");
            }
            System.out.println();
        } while(!enteredPassword.equals(stopWord));
        System.out.println("Программа завершена");
    }
    //01234
    //ABBCD len = 5
    public boolean verify(String password){
        if(wasMaxRepeatsValueSet && wasMinLengthValueSet){
            if(password.length() < minLength){
                return false;
            }
            else{
                int counter = 1;
                for(int i = 0; i < password.length() - 1; i++){
                    for(int j = 1; j < min(maxRepeats, password.length() - i); j++){
                        if(password.charAt(i) == password.charAt(i+j)){
                            counter++;
                        }
                        else{
                            break;
                        }
                        if(counter > maxRepeats) {
                            return false;
                        }
                    }
                }
            }
        }
        else{
            throw new IllegalStateException("Необходимо ввести параметры в чекеры");
        }
        return true;
    }
}

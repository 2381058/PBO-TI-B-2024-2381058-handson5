import java.util.Scanner;

public class Main {
    public static String[] todos = new String[10];
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Before edit");
        addTodoList("Belajar");
        addTodoList("Menggambar");
        addTodoList("Menulis");
        addTodoList("Menyetir");
        System.out.println("After edit");
        editTodoList(2, "Menari");
        showTodolist();
    }

    public static void showTodolist() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos. length; i++){
            String todo = todos[i];
            if (todo != null) {
                System.out.println((i + 1) + ", " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {

        resizeIfFull();

        // add todo to array that has null element
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeIfFull() {
        // chek is full
        boolean isFull = true;
        for (int i= 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }

        // Resize to two times bigger is Full
        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    public static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < todos.length; i++) {
            //if todo is the last element
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                // replace with the element on the right
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    private static boolean isSelectedTodoNotValid(Integer number) {
        // check if the number is less then equal zero
        if (number <= 0) {
            return true;
        }

        // check if the number greater than the todos size/zero
        if (number - 1 > todos.length - 1) {
            return true;
        }

        //check whether the element is already null
        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editTodoList(Integer number,  String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = sc.nextLine();
        return data;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodolist();
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("pilih");

            switch (selectedMenu) {
                case "1":
                    showMenuAddTodoList();
                    break;
                case "2":
                    showMenuRemoveTodolist();
                    break;
                case "3":
                    showMenuEditTodoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
            }
        }
    }

    public static void showMenuAddTodoList() {
        System.out.println("MENAMBAH TODO LIST");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")) {
            //batal
        } else {
            addTodoList(todo);
        }
    }

    public static void   showMenuRemoveTodolist() {
        System.out.println("MEENGHAPUS TODO LIST");
        String number = input("Nomor yang dihapus (x jika batal)");
        if (number.equals("x")) {
            //batal
        } else {
            boolean success = removeTodoList(Integer.parseInt(number));
            if (success) {
                System.out.println("Gagal menghapus todo list : " + number);
            }
        }
    }

    public static void showMenuEditTodoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Masukan nomot todo (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }
        String newTodo = input("Masukan todo yang baru (x jika batal");
        if (newTodo.equals("x")) {
            return;
        }
        boolean isEditTodoSuccess = editTodoList(Integer.parseInt(selectedTodo), newTodo);
        if (isEditTodoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }

}
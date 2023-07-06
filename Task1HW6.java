package HomeWork6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий фильтрации и выведет ноутбуки, отвечающие фильтру.
// NoteBook notebook1 = new NoteBook
// NoteBook notebook2 = new NoteBook
// NoteBook notebook3 = new NoteBook
// NoteBook notebook4 = new NoteBook
// NoteBook notebook5 = new NoteBook

// Например: “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет

// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.
// Класс сделать в отдельном файле

// приветствие
// Выбор параметра
// выбор конкретнее
// вывод подходящих



public class Task1HW6 {
   // Coздание списка ноутбуков
    
    public static void main(String[] args) {
    NoteBook notebook1 = new NoteBook("notebook1", "Asus X147", 2000, 16, 512, "Green", "Linux");
    NoteBook notebook2 = new NoteBook("notebook2", "Acer Work", 2500, 16, 256, "Black", "NO");
    NoteBook notebook3 = new NoteBook("notebook3", "Lenovo Super", 2200, 12, 1024, "Silver", "Windows");
    NoteBook notebook4 = new NoteBook("notebook4", "Honor MATE1", 2400, 12, 256, "Black", "Linux");
    NoteBook notebook5 = new NoteBook("notebook5", "MSI 5", 3100, 32, 512, "Silver", "windows"); 
    NoteBook notebook6 = new NoteBook("notebook6", "HP 58vd", 2100, 8, 1024, "Black", "Linux");
    
    // составление списка ноутбуков
    ArrayList<NoteBook> listing = new ArrayList<>();
    listing.add(notebook1);
    listing.add(notebook2);
    listing.add(notebook3);
    listing.add(notebook4);
    listing.add(notebook5);
    listing.add(notebook6);

    // Запуск основного режима поиска
    ArrayList<NoteBook> oksearch = new ArrayList<>(); // Список для хранения результатов поиска
    ArrayList<NoteBook> okcopy = new ArrayList<>();  // Копия списка для стуреней поиска
    Scanner sc = new Scanner(System.in, "cp866");
    
    int mode = 1;
    while(mode > 0 && mode < 6){
        switch (mode) {
            case 1:
                System.out.print("Введите требуемую наименьшую частоту процессора Гц: ");
                int minfcpu = Integer.parseInt(sc.nextLine());
                oksearch = firstSearch(listing, oksearch, minfcpu);
                break;
            
            case 2:
                System.out.print("Введите требуемый наименьший объем оперативной памяти: ");
                int minramM = Integer.parseInt(sc.nextLine());
                oksearch = intSearch(oksearch, okcopy, minramM, "ramM");
                break;

            case 3:
                System.out.print("Введите требуемый наименьший объем памяти жесткого диска: ");
                int minmemory = Integer.parseInt(sc.nextLine());
                oksearch = intSearch(oksearch, okcopy, minmemory, "memory");
                break;

            case 4:
                HashSet<String> color = new HashSet<>();
                for (NoteBook item : oksearch) color.add(item.retcolor());  
                Map<Integer, String> colormap = new HashMap<>();
                int step = 1;
                for (String item : color) colormap.put(step++, item);
                System.out.println("Возможные варианты цвета ноутбука: ");
                for (Integer key : colormap.keySet()) System.out.println(key + ") "+ colormap.get(key));
                System.out.print("Введите цифру нужого цвета: ");
                int numcolor = Integer.parseInt(sc.nextLine());
                okcopy = copyList(oksearch, okcopy);
                for (NoteBook item : okcopy) if (colormap.get(numcolor) != item.retcolor()) oksearch.remove(item);
                break;

            case 5:
                HashSet<String> winlist = new HashSet<>();
                for (NoteBook item : oksearch) winlist.add(item.retos());  
                Map<Integer, String> winmap = new HashMap<>();
                step = 1;
                for (String item : winlist) winmap.put(step++, item);
                System.out.println("Возможные варианты операционной системы ноутбука: ");
                for (Integer key : winmap.keySet()) System.out.println(key + ") "+ winmap.get(key));  
                System.out.print("Введите цифру нужной операционной системы: ");
                int numos = Integer.parseInt(sc.nextLine());
                okcopy = copyList(oksearch, okcopy);
                for (NoteBook item : okcopy) if (winmap.get(numos) != item.retos()) oksearch.remove(item);
                break;
        }
        if (oksearch.size() == 1) {
            System.out.println("После последнего введенного параметра остался один ноутбук");
            mode = 7;
        }
        else if (oksearch.size() == 0) {
            System.out.println("После последнего введенного параметра нотбуков не осталось");
            mode = -1;
        }
        else mode++;
    }

    if(mode > 0){
        System.out.println("Ниже приведены удовлетворяющие поиску ноутбуки: \n");
        for (NoteBook item : oksearch) System.out.println(item.toString());
    }
    sc.close();
    }
    // Методы:
    // Копирование списка ноутбуков
    public static ArrayList<NoteBook> copyList (ArrayList<NoteBook> input, ArrayList<NoteBook> out){
        out.clear();
       for (NoteBook item : input) out.add(item);
        return out;
    }

    // Первый поиск по частоте процессора
    public static ArrayList<NoteBook> firstSearch (ArrayList<NoteBook> input, ArrayList<NoteBook> out, int minValue){
        for (NoteBook item : input) {
        if(item.retfCPU() >= minValue ) out.add(item);          
        }   
        return out;
    }

    // Поиск по int параметру
    public static ArrayList<NoteBook> intSearch (ArrayList<NoteBook> input, ArrayList<NoteBook> out, int minValue, String mode){
        out = copyList(input, out);
        for (NoteBook item : out) {
            switch (mode) {
                case "ramM":
                    if(item.retramM() < minValue ) input.remove(item); 
                    break;
                case "memory":
                    if(item.retmemory() < minValue ) input.remove(item); 
                    break;
            }          
        }
        return input;
    }
      
}

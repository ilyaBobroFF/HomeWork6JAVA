package HomeWork6;

public class NoteBook {
    private String id;
    private String model;
    private int fCPU;
    private int ramM;
    private int memory;
    private String color;
    private String os;
    
    public NoteBook(String id, String model, int fCPU, int ramM, int memory, String color, String os) {
        this.id = id;
        this.model = model;
        this.fCPU = fCPU;
        this.ramM = ramM;
        this.memory = memory;
        this.color = color;
        this.os = os;
    }

    public String retid(){
        return id;
    }

    public String retModel(){
        return model;
    }

    public int retfCPU(){
        return fCPU;
    }
    public int retramM(){
        return ramM;
    }
    public int retmemory(){
        return memory;
    }
    public String retcolor(){
        return color;
    }
    public String retos(){
        return os;
    }
    
    @Override
    public String toString(){
        return "Модель: " + model 
        + "\n Частота процессора: " + fCPU + "Гц"
        + "\n Объём оперативной памяти: " + ramM + "Гб"
        + "\n Объём жесткого диска: " + memory + "Гб"
        + "\n Цвет: " + color 
        + "\n Операционная система: " + os + "\n";
    }
}

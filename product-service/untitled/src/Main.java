import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.\

class Empl implements Comparable<Empl> {
    private String name;
    private Integer slary;

    public Empl(String name, Integer salary) {
        this.name = name;
        this.slary = salary;
    }

    public String getName() {
        return name;
    }

    public Integer getSlary() {
        return slary;
    }

    @Override
    public String toString(){
        return ("name" + name + "," + "salary" + slary);
    }
    @Override
    public int compareTo(Empl o) {
        return this.slary - o.slary;
    }

}
    public class Main {
    public static void main(String[] args) {
        List<Empl> emp = new ArrayList<>();
        emp.add(new Empl("jai",2000));
        emp.add(new Empl("raj",5000));
        emp.add(new Empl("vishal",1000));

        Collections.sort(emp);
        System.out.println(emp.toString());

    }
}


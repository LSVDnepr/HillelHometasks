package myjdbcapp;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

public class MainApp {
    /*Описание задачи:
    Работаю с базой https://github.com/datacharmer/test_db с таблицами department и employees.
    Connect к базе данных, настройки для коннекта (username, password, URL), получение коннекта находятся в классе DB Worker;
    Все основные методы для работы с таблицей employees реализованы в классе EmployeeWorker
    Все основные методы для работы с таблицей departments реализованы в классе DepWorker
    Для каждой из таблиц реализованы основные методы (insert, update, delete, select by id, select where )


     */




    public static void main(String[] args) throws Exception {

        String username = "username";
        String password = "password";
        String URL = "jdbc:mysql://localhost:3306/employees";

        DBWorker dbW=new DBWorker(username,password,URL);
        DepWorker depWorker=new DepWorker(dbW);
        EmployeeWorker empWorker=new EmployeeWorker(dbW);

       /* depWorker.deleteDep("d010");
        depWorker.deleteDep("d011");*/


        System.out.println("Вношу в базу новый департамент");
        Department dep=depWorker.insertDep(new Department("Science and Innovation"));
        System.out.println(dep);
        System.out.println("\nВношу в базу новый департамент");
        Department dep2=depWorker.insertDep(new Department("Logistics"));
        System.out.println(dep2);
        System.out.println("\nМеняю в базе имя департамента: ");
        dep2.setDeptName("Logistics and Storage");
        depWorker.updateDep(dep2);
        System.out.println(dep2);
        System.out.println("\nВыбираю из базы все департаменты, сортирую по возрастанию номера департамента: ");
        LinkedList<Department> list=depWorker.selectDepWhere("order by dept_no");
        for (ListIterator li=list.listIterator();li.hasNext();){
            System.out.println(li.next());
        }

        System.out.println("\nУдаляю оба внесенных департамента и повторно делаю выборку всех департаментов: ");
        depWorker.deleteDep(dep);
        depWorker.deleteDep("d011");
        list=depWorker.selectDepWhere("order by dept_no");
        for (ListIterator li=list.listIterator();li.hasNext();){
            System.out.println(li.next());
        }

        System.out.println("\nДобавляю в базу нового сотрудника:");
        Employee emp=new Employee("Keira","Knightley", LocalDate.of(1985,3,26),Gender.F, LocalDate.of(2008,6,26));
        emp=empWorker.insertEmp(emp);
        System.out.println(emp);

        System.out.println("\nДобавляю в базу нового сотрудника:");
        Employee emp2=new Employee("Cara","Delevingne",LocalDate.of(1992,8,12),Gender.F,LocalDate.of(2012,11,21));
        emp2=empWorker.insertEmp(emp2);
        System.out.println(emp2);

        System.out.println("\nРедактирую в базе данные о сотруднике");
        emp2.setFirstName("Jane");
        emp2.setLastName("Smith");
        empWorker.updateEmp(emp2);
        System.out.println(emp2);

        System.out.println("\nВыбираю из базы 10 сотрудников с максимальным emp_no, сортирую по убыванию, вывожу в консоль список");
        LinkedList<Employee> empList=empWorker.selectEmpWhere("order by emp_no desc limit 10");
        for (ListIterator<Employee> li=empList.listIterator();li.hasNext();){
            System.out.println(li.next());
        }

        System.out.println("\nУдаляю добавленных сотрудников");
        empWorker.deleteEmployee(emp);
        empWorker.deleteEmployee(emp2);
        System.out.println("\nВыбираю из базы 5 сотрудников с максимальным emp_no, сортирую по убыванию, вывожу в консоль список");
        empList=empWorker.selectEmpWhere("order by emp_no desc limit 10");
        for (ListIterator<Employee> li=empList.listIterator();li.hasNext();){
            System.out.println(li.next());
        }



        System.out.println("\nВыбираю из базы сотрудника по emp_no=10004");
        Employee emp3=empWorker.getEmpByEmpNo(10004);
        System.out.println(emp3);













    }
}

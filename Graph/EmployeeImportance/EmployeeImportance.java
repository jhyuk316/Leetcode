package Graph.EmployeeImportance;
// 690. Employee Importance
// https://leetcode.com/problems/employee-importance/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};


// O(N) DFS
class Solution {
    Map<Integer, Employee> graph = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee emp : employees) {
            graph.put(emp.id, emp);
        }

        return dfs(id);
    }

    public int dfs(int id) {
        int res = 0;
        for (int subordinate : graph.get(id).subordinates) {
            res += dfs(subordinate);
        }

        res += graph.get(id).importance;
        return res;
    }
}


public class EmployeeImportance {
    public static void main(String[] args) {

    }
}

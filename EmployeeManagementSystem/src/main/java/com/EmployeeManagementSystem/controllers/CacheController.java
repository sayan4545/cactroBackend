package com.EmployeeManagementSystem.controllers;

import com.EmployeeManagementSystem.entities.EmployeeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2.0")
public class CacheController {
    private static final int MAX_SIZE = 10;
    private final Map<Long, EmployeeEntity> cache = new LinkedHashMap<Long, EmployeeEntity>(MAX_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, EmployeeEntity> eldest) {
            return size() > MAX_SIZE;
        }
    };

    @PostMapping
    public ResponseEntity<String> store(@RequestBody EmployeeEntity employee) {
        synchronized (cache) {
            if (cache.size() >= MAX_SIZE && !cache.containsKey(employee.getEmpId())) {
                return ResponseEntity.badRequest().body("Cache is full. Cannot add new employees.");
            }
            cache.put(employee.getEmpId(), employee);
        }
        return ResponseEntity.ok("Employee stored successfully");
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeEntity> retrieve(@PathVariable Long empId) {
        synchronized (cache) {
            return cache.containsKey(empId)
                    ? ResponseEntity.ok(cache.get(empId))
                    : ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> remove(@PathVariable Long empId) {
        synchronized (cache) {
            if (cache.remove(empId) != null) {
                return ResponseEntity.ok("Employee removed successfully");
            }
            return ResponseEntity.notFound().build();
        }
    }

}

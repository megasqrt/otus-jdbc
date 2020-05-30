package ru.kan.otus.tasker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.stereotype.Service;
import ru.kan.otus.tasker.domain.AclEntry;
import ru.kan.otus.tasker.domain.AclObject;
import ru.kan.otus.tasker.domain.Task;
import ru.kan.otus.tasker.repositories.AclEntryRepository;
import ru.kan.otus.tasker.repositories.AclObjectRepository;
import ru.kan.otus.tasker.repositories.AclSidRepository;
import ru.kan.otus.tasker.repositories.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final AclSidRepository aclSidRepo;
    private final AclObjectRepository aclObjectRepo;
    private final AclEntryRepository aclEntryRepo;


    @HystrixCommand(fallbackMethod = "defaultList", commandKey = "Task")
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "defaultVoid", commandKey = "Task")
    public void deleteById(Task task) {
        taskRepo.delete(task);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "defaultVoid", commandKey = "Task")
    public void delete(Task task) {
        taskRepo.delete(task);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "defaultVoid", commandKey = "Task")
    public void save(Task task) {
        taskRepo.save(task);
    }


    public List<Task> defaultList() {
        List<Task> nullTaskList = Arrays.asList(Task.builder().id(0)
                .description("NaN")
                .build());
        return nullTaskList;
    }

    public void defaultVoid(Task task) {
    }

    public Optional<Task> findById(long id) {
        return taskRepo.findById(id);
    }

    public void addAclEntries(Task task) {
        Long adminSidId = aclSidRepo.findBySid("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("ROLE_ADMIN is unavailable")).getId();
        Long userSidId = aclSidRepo.findBySid("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE_USER is unavailable")).getId();

        AclObject aclObject = AclObject.builder()
                .objectIdclass(1L)
                .objectIdentity(task.getId())
                .parentObject(null)
                .ownerSid(1L)
                .entriesInheriting(false).build();

        aclObjectRepo.save(aclObject);

        List<AclEntry> aclEntryList = Arrays.asList(
                AclEntry.builder()
                        .aclObjectIdentity(task.getId())
                        .aceOrder(1)
                        .mask(BasePermission.READ.getMask())
                        .sid(adminSidId)
                        .granting(true)
                        .auditSuccess(true)
                        .auditFailure(true)
                        .build(),
                AclEntry.builder()
                        .aclObjectIdentity(task.getId())
                        .aceOrder(2)
                        .mask(BasePermission.WRITE.getMask())
                        .sid(adminSidId)
                        .granting(true)
                        .auditSuccess(true)
                        .auditFailure(true)
                        .build(),
                AclEntry.builder()
                        .aclObjectIdentity(task.getId())
                        .aceOrder(3)
                        .mask(BasePermission.READ.getMask())
                        .sid(userSidId)
                        .granting(true)
                        .auditSuccess(true)
                        .auditFailure(true)
                        .build());

        aclEntryRepo.saveAll(aclEntryList);
    }
}

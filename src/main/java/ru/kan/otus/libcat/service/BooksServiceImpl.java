package ru.kan.otus.libcat.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.stereotype.Service;
import ru.kan.otus.libcat.domain.AclEntry;
import ru.kan.otus.libcat.domain.AclObject;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.AclEntryRepository;
import ru.kan.otus.libcat.repositories.AclObjectRepository;
import ru.kan.otus.libcat.repositories.AclSidRepository;
import ru.kan.otus.libcat.repositories.BooksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BooksRepository bookRepo;
    private final AclEntryRepository aclEntryRepo;
    private final AclSidRepository aclSidRepo;
    private final AclObjectRepository aclObjectRepo;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public Books createBook(Books book) {
        Books savedBook = bookRepo.save(book);
        addAclEntries(book);
        return savedBook;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public Books save(Books book) {
        return bookRepo.save(book);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public void delete(Long bookId) {
        bookRepo.deleteById(bookId);
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    @Override
    public List<Books> findAll() {
        return bookRepo.findAll();
    }

    @PostAuthorize("hasPermission(returnObject.orElse(null), 'READ')")
    @Override
    public Optional<Books> findById(Long bookId) {
        return bookRepo.findById(bookId);
    }

    public void addAclEntries(Books book) {
        Long adminSidId = aclSidRepo.findBySid("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("ROLE_ADMIN is unavailable")).getId();
        Long userSidId = aclSidRepo.findBySid("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE_USER is unavailable")).getId();

        AclObject aclObject = AclObject.builder()
                .objectIdclass(1L)
                .objectIdentity(book.getId())
                .parentObject(null)
                .ownerSid(1L)
                .entriesInheriting(false).build();

        aclObjectRepo.save(aclObject);

        List<AclEntry> aclEntryList = Arrays.asList(
                AclEntry.builder()
                        .aclObjectIdentity(book.getId())
                        .aceOrder(1)
                        .mask(BasePermission.READ.getMask())
                        .sid(adminSidId)
                        .granting(true)
                        .auditSuccess(true)
                        .auditFailure(true)
                        .build(),
                AclEntry.builder()
                        .aclObjectIdentity(book.getId())
                        .aceOrder(2)
                        .mask(BasePermission.WRITE.getMask())
                        .sid(adminSidId)
                        .granting(true)
                        .auditSuccess(true)
                        .auditFailure(true)
                        .build(),
                AclEntry.builder()
                        .aclObjectIdentity(book.getId())
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
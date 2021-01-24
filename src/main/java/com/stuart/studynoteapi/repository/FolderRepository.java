package com.stuart.studynoteapi.repository;

import com.stuart.studynoteapi.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}

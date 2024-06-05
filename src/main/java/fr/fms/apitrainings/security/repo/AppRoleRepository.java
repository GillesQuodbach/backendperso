package fr.fms.apitrainings.security.repo;

import fr.fms.apitrainings.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRolename(String rolename); // à partir de nom d'utilisateur
}

package com.tipiniquim.os.repositories;

import com.tipiniquim.os.model.entity.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {
}

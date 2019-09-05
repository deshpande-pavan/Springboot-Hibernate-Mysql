package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

}

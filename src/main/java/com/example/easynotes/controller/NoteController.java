package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Notes;
import com.example.easynotes.repository.NotesRepository;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	private NotesRepository noteRepository;

	@GetMapping("/notes")
	public List<Notes> getAll() {
		return noteRepository.findAll();
	}

	@PostMapping("/notes")
	public Notes createNote(@Valid @RequestBody Notes note) {
		return noteRepository.save(note);
	}

	@GetMapping("/notes/{id}")
	public Notes getNoteById(@PathVariable(value = "id") Long noteId) throws ResourceNotFoundException {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	@PutMapping("/notes/{id}")
	public Notes updateNotes(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Notes noteDetails)
			throws ResourceNotFoundException {

		Notes note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "noteId", noteId));
		note.setContent(noteDetails.getContent());
		note.setTitle(noteDetails.getTitle());
		return noteRepository.save(note);
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNode(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

		Notes note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		noteRepository.delete(note);
		return ResponseEntity.ok().body(note.toString()+" is deleted from the database");
	}
}

package com.example.easynotes.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.utils.EmailNotificationImpl;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	private EmailNotificationImpl emailNotificationImpl;

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		noteRepository.delete(note);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/notes/sendMail")
	public ResponseEntity<?> sendMail(@RequestParam String emailId, @RequestParam String name,
			@RequestParam(required = false) MultipartFile file) {
		Map<String, Object> emailMap = emailNotificationImpl.insertEmailRecordForRest(emailId, name);
		emailNotificationImpl.sendEmailNotification(emailMap, emailId, true, file, null);
		return ResponseEntity.ok().build();
	}

}

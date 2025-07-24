package com.project.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {
	
	private HttpStatus responseStatus; // For Status Code
	
	private String status;	// Success or Failure
	
	private String message; // Printing message
	
	private Object data;	// Displaying data
	
	private String timestamp;
	
	public ResponseEntity<?> create() {
		Map<String, Object> map = new LinkedHashMap<>();
		
		map.put("status", status);
		map.put("message", message);
		
		if(!ObjectUtils.isEmpty(data)) {
			map.put("data", data);
		}
		map.put("timestamp", getCurrentSystemTime());
		
		return new ResponseEntity<>(map, responseStatus);
	}
	
	 private String getCurrentSystemTime() {
	        // Gets system default timezone (e.g., Asia/Kolkata)
	        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
	        return now.atZone(ZoneId.systemDefault()).format(formatter);
	    }
	
}
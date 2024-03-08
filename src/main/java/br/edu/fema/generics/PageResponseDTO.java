package br.edu.fema.generics;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
	private List<T> items;
	private boolean hasNext;
	private long totalElements;
	private int totalPages;
}

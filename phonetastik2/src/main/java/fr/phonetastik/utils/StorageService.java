package fr.phonetastik.utils;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

//	void init() throws IOException;

//	public void deleteAll();

	void store(MultipartFile file, String nomFichier);

	//Map<String, File> getMap();

}

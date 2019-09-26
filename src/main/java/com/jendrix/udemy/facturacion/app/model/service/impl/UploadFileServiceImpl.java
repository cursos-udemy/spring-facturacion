package com.jendrix.udemy.facturacion.app.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jendrix.udemy.facturacion.app.model.service.UploadFileService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	// TODO: read from properties
	private static final String UPLOAD_FOLDER = "/tmp/uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Resource resource = new UrlResource(getPath(filename).toUri());
		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException("Unable to load file " + filename);
		}

		return resource;
	}

	@Override
	public String upload(MultipartFile file) throws IOException {
		String filename = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename());
		Files.write(getPath(filename), file.getBytes());
		return filename;
	}

	@Override
	public boolean delete(String filename) {
		if (filename != null) {
			File file = getPath(filename).toFile();
			if (file.exists() && file.canRead()) {
				return file.delete();
			}
		}
		return false;
	}

	private Path getPath(String filename) {
		return Paths.get(UPLOAD_FOLDER).resolve(filename);
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(getPath(UPLOAD_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectories(getPath(UPLOAD_FOLDER));
	}
}

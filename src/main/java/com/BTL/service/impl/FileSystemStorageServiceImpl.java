package com.BTL.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BTL.config.StorageProperties;
import com.BTL.exception.StorageFileNotFoundException;
import com.BTL.service.StorageService;

@Service
public class FileSystemStorageServiceImpl implements StorageService {
	 final Path rootLocation;// đường dẫn lưu hình
	
	 //tạo file lưu trữ với tên truyền vào để kluwu file đó
	@Override
	public String getStoredFilename(MultipartFile file, String id) {
		//filenameUtils lấy ra loại file .png hay jpg
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}
	
	//contructor cần lưu trữ
	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation=Paths.get(properties.getLocation());
	}
	//dùng để lưu thông tin từ multipartFile
	@Override
	public void store(MultipartFile file, String storedFilename) {
		try {
			//nesu file upload đến hệ thống là rỗng
			if(file.isEmpty()) {
				// báo ra hệ thống là file rỗng
				throw new StorageFileNotFoundException("Failed to store empty file");
			}
			//Tính toán 
			//lấy thông tin file name, chuẩn hó normalize, và đổi đường dẫn thành tuyệt đố() absolute
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
					.normalize().toAbsolutePath();
			
			
			if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageFileNotFoundException("Cannot store file outside");
			}
			
			//tạo inputStream, gọi copy copy dạng thay thế file hiện tại
			try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageFileNotFoundException("Failed to store file" + e);
		}
	}
	
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			//
			Resource resource = new UrlResource(file.toUri());
			// nếu tồn tại, và có thể đọc được 
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundException("Could not read file: " + filename);
		}catch (Exception e) {
			// TODO: handle exception
			throw new StorageFileNotFoundException("Could not read: " + filename);
		}
	}
	
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	//xóa khi không cần
	@Override
	public void delete(String storedFile) throws IOException {
		Path destinationFile =rootLocation.resolve(Paths.get(storedFile)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}
	
	//khởi tạo thư mục
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.print(rootLocation.toString());
		}catch (Exception e) {
			// TODO: handle exception
			throw new StorageFileNotFoundException("could not init storage");
		}
	}
}

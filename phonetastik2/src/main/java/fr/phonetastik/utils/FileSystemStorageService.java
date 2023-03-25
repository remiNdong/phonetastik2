package fr.phonetastik.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class FileSystemStorageService implements StorageService {

	private String bucketName = "phonetastikbucket";
	private String accesskey = "AKIAUF7XY3TSAFNGGPNL";
	private String secretkey = "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";
	private AmazonS3 s3client;
	//private Map<String, File> map;

	public AmazonS3 s3client() {

		if (s3client == null) {
			AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
			s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion("eu-west-3").build();
		}
		return s3client;

	}

	/*
	 * @Override public void init() throws IOException { toString();
	 * 
	 * map=getFiles();
	 * 
	 * }
	 */

	/*
	 * public void deleteFile(File file ) throws IOException {
	 * Files.delete(file.toPath()); }
	 */

	@Override
	public void store(MultipartFile multipartFile, String nomFichier) {
		try {

			if (multipartFile.isEmpty()) {
				throw new StorageException("Le fichier est vide");
			}

			if (!isImage(multipartFile)) {
				throw new StorageException("Le fichier n'est pas une image");
			}

			if (!isAtSize(multipartFile)) {
				throw new StorageException("Le fichier ne doit pas depasser 128 Ko");
			}

			enregistrer(multipartFile, nomFichier);

		} catch (Exception e) {
			throw new StorageException("Failed to store file. " + e.getMessage(), e);
		}
	}

	public void enregistrer(MultipartFile multipartFile, String nomFichier) {
		try {
			// Files.copy(multipartFile.getInputStream(),
			// this.root.resolve(multipartFile.getOriginalFilename()));
			ObjectMetadata data = new ObjectMetadata();
			data.setContentType(multipartFile.getContentType());
			data.setContentLength(multipartFile.getSize());

			s3client().putObject(bucketName, nomFichier, multipartFile.getInputStream(), data);
			// map.put(nomFichier, file);

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public boolean isImage(MultipartFile multipartFile) {

		String mimeType = multipartFile.getContentType();

		return mimeType.substring(0, 5).equalsIgnoreCase("image");

	}

	public boolean isAtSize(MultipartFile multipartFile) {
		long maxsize = 128 * 1024;
		return maxsize >= multipartFile.getSize();

	}

	/*
	 * public File getFile(String nomFile) throws IOException { S3Object s3object =
	 * s3client().getObject(bucketName, nomFile); S3ObjectInputStream inputStream =
	 * s3object.getObjectContent();
	 * 
	 * return file;
	 * 
	 * }
	 */

	/*
	 * public Map<String, File> getFiles() throws IOException {
	 * 
	 * Map<String, File> map = new HashMap<String, File>();
	 * 
	 * ObjectListing objectListing = s3client().listObjects(bucketName); for
	 * (S3ObjectSummary os : objectListing.getObjectSummaries()) {
	 * map.put(os.getKey(), getFile(os.getKey())); }
	 * 
	 * return map;
	 * 
	 * }
	 * 
	 * public Map<String, File> getMap(){ return map; }
	 */

}

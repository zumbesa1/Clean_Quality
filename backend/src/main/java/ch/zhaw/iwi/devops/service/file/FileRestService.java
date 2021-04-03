package ch.zhaw.iwi.devops.service.file;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;
import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.devops.model.file.File;
import ch.zhaw.iwi.devops.service.AbstractCrudRestService;
import ch.zhaw.iwi.devops.service.PathListEntry;

public class FileRestService extends AbstractCrudRestService<File, Long, FileDatabaseService> {

	private static final Logger logger = LoggerFactory.getLogger(FileRestService.class);
	
	@Inject
	public FileRestService(Injector injector) {
		super(injector, FileDatabaseService.class);
	}

	@Override
	protected void initGet() {
		super.initGet();

		get("services/upload/:fileKey", (req, res) -> {
			Long fileKey = Longs.tryParse(req.params("fileKey"));
			File file = getCrudDatabaseService().read(fileKey);

			res.header("Content-Description", "File Transfer");
			res.header("Content-Type", "application/force-download");
			res.header("Content-Type", "application/download");
			res.header("Content-Disposition", "attachment;filename=" + file.getName());

			try {
				HttpServletResponse raw = res.raw();
				raw.getOutputStream().write(file.getContent());
				raw.getOutputStream().flush();
				raw.getOutputStream().close();
			} catch (IOException e) {
				logger.error("Could not read file", e);
			}
			return res;
		});
	}

	@Override
	protected void initPost() {
		super.initPost();

		post("services/upload", (req, res) -> {
			byte[] content = null;
			req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			Part part = req.raw().getPart("upload");
			try (InputStream is = part.getInputStream()) {
				content = ByteStreams.toByteArray(is);
			} catch (IOException e) {
				logger.error("File upload failed", e);
			}
			if (content != null) {
				File file = new File();
				file.setContent(content);
				file.setName(part.getSubmittedFileName());
				file.setSize(part.getSize());
				file.setMimetype(part.getContentType());
				file = getCrudDatabaseService().create(file);
				PathListEntry<Long> result = new PathListEntry<>();
				getCrudDatabaseService().createPathListEntry(file, result);
				return result;
			} else {
				return "no content";
			}
		}, getJsonTransformer());

	}

}
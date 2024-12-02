package GUI.applications;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class T {
    public static void main(String[] args) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        // Gán giá trị "Thomas" cho biến name để lát nữa bind dữ liệu
        context.setVariable("name", "Thomas");
        // Trả về chuỗi là html string sau khi thực hiện bind dữ liệu
        String html = templateEngine.process("template", context);


        try {
            OutputStream outputStream = new FileOutputStream("message.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }
}

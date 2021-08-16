package edu.cczu.tmall.controller;

import edu.cczu.tmall.domain.Category;
import edu.cczu.tmall.service.CategoryService;
import edu.cczu.tmall.util.ImageUtil;
import edu.cczu.tmall.util.Page;
import edu.cczu.tmall.util.UUIDUtil;
import edu.cczu.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:48
 */

@Controller
@RequestMapping(path = "")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    /**
     * Category分页查询
     * @param page 分页条件
     * @return  分页后的数据
     */
    @RequestMapping(path = "/admin_category_list.do")
    public ModelAndView doList(Page page){
        ModelAndView mv = new ModelAndView();
        List<Category> categories = categoryService.list(page);
        int total = categoryService.total();
        page.setTotal(total);
        mv.addObject("categories", categories);
        mv.addObject("page", page);
        mv.setViewName("/admin/listCategory");
        return mv;
    }

    /**
     * Category新增add方法
     * @param category Category对象(包含了新增分类的信息)用于接收页面提交的分类名称
     * @param session 会话用于在后续获取当前应用的路径
     * @param uploadedImageFile 用于接受文件上传的图片
     * @return 重定向到/admin_category_list.do
     * @throws IOException
     * @description
     * 1、doAdd()方法映射路径admin_category_add.do的访问
     * 2、参数Category category用于接收页面提交的分类名称
     * 3、参数session用于在后续获取当前应用的路径
     * 4、UploadedImageFile用于接受文件上传的图片
     * 5、通过categoryService保存category对象
     * 6、通过session获取ServletContext，再通过getRealPath定位存放分类图片的路径。
     * 7、根据分类id创建文件名
     * 8、如果/img/category目录不存在，则创建该目录，否则后续保存浏览器传过来的图片，会提示无法保存。
     * 9、通过UploadedImageFile把浏览器传递过来的图片保存在上述指定位置。
     * 10、通过ImageUtil.change2jpg(file)确保图片格式一定是jgp，而不仅仅是后缀名为jpg的图片。
     * 11、客户端跳转到admin_category_list.do
     */
    @RequestMapping(path = "/admin_category_add.do")
    public String doAdd(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        // 优化：主键字段采用UUID
        category.setId(UUIDUtil.getUUID());
        // 5、通过categoryService保存category对象
        int count = categoryService.add(category);
        if(1 == count){
            // 拿到当前应用路径
            // 6、通过session获取ServletContext，再通过getRealPath定位存放分类图片的路径。
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));

            File file = new File(imageFolder, category.getId() + ".jpg");
            if(!file.getParentFile().exists()){
                // 7、根据分类id创建文件名
                file.getParentFile().mkdirs();
            }
            // 9、通过UploadedImageFile把浏览器传递过来的图片保存在上述指定位置。
            uploadedImageFile.getImage().transferTo(file);

            //  10、通过ImageUtil.change2jpg(file)确保图片格式一定是jgp，而不仅仅是后缀名为jpg的图片。
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);

            // 11、客户端跳转到admin_category_list.do
            return "redirect:/admin_category_list.do";
        }
        return null;
    }

    /**
     * Category做删除方法
     * @param id 删除的记录的依据(根据主键字段id删除)
     * @param session 用于后续定位文件位置
     * @return 重定向到/admin_category_list.do
     * @desctiption 删除的类别的方法
     * 1、映射路径admin_category_delete.do
     * 2、提供参数接受id注入
     * 3、提供session参数，用于后续定位文件位置
     * 4、通过categoryService删除数据
     * 5、通过session获取ControllerContext然后获取分类图片位置，接着删除分类图片
     * 6、重定向到admin_category_list.do
     */
    @RequestMapping(path = "admin_category_delete.do")
    public String doDelete(String id, HttpSession session) throws IOException{
        categoryService.delete(id);
        // 影响的记录条数==1：删除成功
        // 删除记录成功，连同文件系统下的文件一起删除
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return "redirect:/admin_category_list.do";
    }

    /**
     * Category做编辑操作(只用于回显数据)
     * @param model Model
     * @param id 主键字段
     * @return 请求转发的页面
     * @description 1、根据id查询出对应的记录信息
     * 2、将Category记录信息添加到request域中，用于后期数据回显以及数据传递
     * 3、请求转发到editCategory.jsp页面
     */
    @RequestMapping(path = "/admin_category_edit.do")
    public String doEdit(Model model, @RequestParam(value = "id") String id){

        // 1、根据id查询出对应的记录信息
        Category category = categoryService.queryCategoryById(id);

        // 2、将Category记录信息添加到request域中，用于后期数据回显以及数据传递
        model.addAttribute("category", category);

        // 3、请求转发到editCategory.jsp页面
        return "/admin/editCategory";
    }

    /**
     * 新增的update方法
     * @param category 接受页面提交的分类名称
     * @param session 用于后续获取当前项目的路径
     * @param uploadedImageFile 用于接收上传的图片
     * @return 客户端重定向到admin_category_list.do
     * @description 1、doUpdate方法映射路径admin_category_update.do
     * 2、参数Category category用于接收分类名称
     * 3、参数session用于在后续获取当前项目的路径
     * 4、UploadedImageFile用于接收文件上穿的图片
     * 5、通过categoryService更新Category对象
     * 6、首先判断是否具有上传图片，如果有，那么通过session获取ControllerContext，再通过getRealPath定位存放分类图片的路径。
     * 7、根据分类id创建文件名
     * 8、通过UploadedImageFile把浏览器穿年底过来的图片保存在上述指定的位置
     * 9、通过ImageUtil.change2jpg(file)确保图片格式一定是jpg。
     * 10、客户端重定向到admin_category_list.do
     */
    @RequestMapping(path = "/admin_category_update.do")
    public String doUpdate(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {

        // 1、更新Category对象
        categoryService.update(category);
        // 2、判断是否具有更新上传的文件图片
        MultipartFile image = uploadedImageFile.getImage();
        if(null != image && !image.isEmpty()){
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            System.out.println("test==========================:" + imageFolder);
            File file = new File(imageFolder, category.getId() + ".jpg");
            if(!file.getParentFile().exists()){
                // 根据分类id创建文件名
                file.getParentFile().mkdirs();
            }
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list.do";
    }
}

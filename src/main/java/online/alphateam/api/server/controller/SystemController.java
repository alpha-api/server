package online.alphateam.api.server.controller;

import online.alphateam.api.server.annotation.CurrentUser;
import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.*;
import online.alphateam.api.server.bean.po.SysDatasource;
import online.alphateam.api.server.bean.po.SysModule;
import online.alphateam.api.server.bean.po.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import online.alphateam.api.server.service.SystemService;
import online.alphateam.api.server.util.Result;
import java.util.List;

@RequestMapping("/sys")
@Controller
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	@Autowired 
	private SystemService systemService;
	@ResponseBody
	@PostMapping("/login")
	public Result<String> login(@RequestBody @Validated LoginParam loginParam){
		Result<String> result=new Result<String>();
		String token=systemService.login(loginParam);
		result.success("登陆成功", token);		
		return result;
	}

	@ResponseBody
	@GetMapping("/datasource")
	public Result<List<SysDatasource>> queryAllSysDatasource() {
		Result<List<SysDatasource>> result=new Result<>();
		List<SysDatasource> datasourceList = systemService.queryAllSysDatasource();
		result.success(datasourceList);
		return result;
	}

	@ResponseBody
	@PostMapping("/datasource")
	public Result<String> saveDatasource(@RequestBody @Validated(DatasourceParam.SaveGroup.class) DatasourceParam param,
										 @CurrentUser SysUser user) {
		Result<String> result = new Result<>();
		systemService.saveSysDatasource(param, user);
		result.successMsg("新增成功");
		return result;
	}

	@ResponseBody
	@PutMapping("/datasource")
	public Result<String> updateDatasource(@RequestBody @Validated(DatasourceParam.UpdateGroup.class) DatasourceParam param,
										 @CurrentUser SysUser user) {
		Result<String> result = new Result<>();
		systemService.updateSysDatasource(param, user);
		result.successMsg("更新成功");
		return result;
	}

	@ResponseBody
	@DeleteMapping("/datasource/{id}")
	public Result<String> deleteDatasource(@PathVariable("id") Integer datasourceId, @CurrentUser SysUser user) {
		Result<String> result = new Result<>();
		systemService.deleteSysDatasource(datasourceId, user);
		result.successMsg("删除成功");
		return result;
	}

	@ResponseBody
	@PostMapping("/modules")
	public Result<String> saveSysModule(@RequestBody @Validated(ModuleParam.SaveGroup.class) ModuleParam moduleParam,
										@CurrentUser SysUser user) {
		Result<String> result=new Result<>();
		systemService.saveSysModule(moduleParam, user);
		result.successMsg("新增成功");
		return result;
	}

	@ResponseBody
	@PutMapping("/modules")
	public Result<String> updateSysModule(@RequestBody @Validated(ModuleParam.UpdateGroup.class) ModuleParam moduleParam,
										  @CurrentUser SysUser user) {
		Result<String> result=new Result<>();
		systemService.updateSysModule(moduleParam, user);
		result.successMsg("更新成功");
		return result;
	}

	@ResponseBody
	@DeleteMapping("/modules/{id}")
	public Result<String> deleteSysModule(@PathVariable("id") Integer moduleId, @CurrentUser SysUser user) {
		Result<String> result=new Result<>();
		systemService.deleteSysModule(moduleId, user);
		result.successMsg("删除成功");
		return result;
	}

	@ResponseBody
	@GetMapping("/modules/{id}")
	public Result<SysModule> querySysModuleByCode(@PathVariable("id") Integer moduleId) {
		Result<SysModule> result = new Result<>();
		SysModule module = systemService.querySysModuleById(moduleId);
		result.success(module);
		return result;
	}

	@ResponseBody
	@GetMapping("/modules")
	public Result<List<SysModule>> queryAllSysModules() {
		Result<List<SysModule>> result = new Result<>();
		List<SysModule> modules = systemService.queryAllSysModule();
		result.success(modules);
		return result;
	}

	@ResponseBody
	@PostMapping("/api")
	public Result<Long> saveSysApi(@RequestBody @Validated(ApiParam.SaveGroup.class) ApiParam apiParam, @CurrentUser SysUser user) {
		Result<Long> result = new Result<>();
		long id = systemService.saveSysApi(apiParam, user);
		result.success("新增成功", id);
		return result;
	}

	@ResponseBody
	@PutMapping("/api")
	public Result<String> updateSysApi(@RequestBody @Validated(ApiParam.SaveGroup.class) ApiParam apiParam, @CurrentUser SysUser user) {
		Result<String> result = new Result<>();
		systemService.updateSysApi(apiParam, user);
		result.success("更新成功");
		return result;
	}

	@ResponseBody
	@DeleteMapping("/api/{id}")
	public Result<String> deleteSysApi(@PathVariable("id") Integer apiId, @CurrentUser SysUser user) {
		Result<String> result = new Result<>();
		systemService.deleteSysApi(apiId, user);
		result.success("删除成功");
		return result;
	}

	@ResponseBody
	@GetMapping("/api/{id}")
	public Result<SysApiDTO<?>> getSysApi(@PathVariable("id") Integer apiId) {
		Result<SysApiDTO<?>> result = new Result<>();
		SysApiDTO<?> api = systemService.getSysApi(apiId);
		result.success(api);
		return result;
	}

	@ResponseBody
	@GetMapping("/api")
	public Result<List<SysApiDTO>> querySysApi(@RequestParam Integer moduleId, @RequestParam Integer datasourceId) {
		Result<List<SysApiDTO>> result = new Result<>();
		List<SysApiDTO> apis = systemService.querySysApi(moduleId, datasourceId);
		result.success(apis);
		return result;
	}

	@ResponseBody
	@PostMapping("/alpha")
	public Result<Long> saveAlpha(@RequestBody @Validated(AlphaParam.SaveGroup.class) AlphaParam param) {
		Result<Long> result = new Result<>();
		long id = systemService.saveSysApiAlpha(param);
		result.success(id);
		return result;
	}

	@ResponseBody
	@PutMapping("/alpha")
	public Result<Long> updateAlpha(@RequestBody @Validated(AlphaParam.UpdateGroup.class) AlphaParam param) {
		Result<Long> result = new Result<>();
		systemService.updateSysApiAlpha(param);
		result.successMsg("更新成功");
		return result;
	}

	@ResponseBody
	@DeleteMapping("/alpha/{id}")
	public Result<Long> DeleteAlpha(@PathVariable("id") Integer alphaId) {
		Result<Long> result = new Result<>();
		systemService.deleteSysApiAlpha(alphaId);
		result.successMsg("删除成功");
		return result;
	}
}

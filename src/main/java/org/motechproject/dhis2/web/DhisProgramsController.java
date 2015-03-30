package org.motechproject.dhis2.web;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DhisProgramsController {
    private ProgramService programService;

    @Autowired
    public DhisProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @RequestMapping(value="/programs", method = RequestMethod.GET)
    @ResponseBody
    public List<Program> getPrograms() {
        return programService.findAll();
    }

}

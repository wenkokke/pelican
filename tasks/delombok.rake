module Delombok
  LOMBOK = 'org.projectlombok:lombok:jar:0.11.6'
  
  def delombok(src=nil)
    if src.nil?
      sources.each do |src| delombok(src) end
    else
      # set default value for target
      target = project._(:target,'src-delomboked',src[project._(:src).length .. -1])
      
      # create filetask for delomboked
      file target => src do
        # calculate source and target
        puts "Delombok #{project.name} into #{target}"
        # run java delombok
        jarfile = project.repositories.locate(LOMBOK)
        classpath = dependencies.join(File::PATH_SEPARATOR)
        Java::Commands.java('-jar',jarfile,'delombok',src,'-d',target,'--classpath',classpath)
      end
      
      # remove old and add delomboked
      sources.reject! {|f| f == src}
      sources << target
    end
    with LOMBOK
  end
end

class Buildr::CompileTask
  include Delombok
end

class Rake::TestTask
  include Delombok
end

class Buildr::Options
  def delombok=val
    @delombok=val
  end
  def delombok?
    @delombok
  end
end
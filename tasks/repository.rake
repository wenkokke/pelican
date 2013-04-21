# Install project to default repository.
task 'create-repository' => :package do
  repo = SemAnTE['Repository','Default']
  File.open("#{repo}/pipeline.spec",'w') do |spec|
    projects.each do |project|
      file = project.package.to_s
      path = "#{repo}/#{project.to_s.gsub(/:/,'-')}/#{project.version}"
      
      if File.exist?(file)
        mkdir_p path
        cp file,path
        spec.puts project.package.to_spec
      end
    end
  end
end
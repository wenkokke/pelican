# maven repositories
repositories.remote << 'http://repo1.maven.org/maven2'

# maven dependencies
LOMBOK      = 'org.projectlombok:lombok:jar:0.11.8'
JUNIT4      = 'junit:junit:jar:4.10'
JPARSEC     = 'jparsec:jparsec:jar:2.0.1'
GUAVA       = 'com.google.guava:guava:jar:13.0.1'
SNAKE_YAML  = 'org.yaml:snakeyaml:jar:1.8'
COMMONS_CLI = 'commons-cli:commons-cli:jar:1.2'
SLF4J       = struct(
  :api     => 'org.slf4j:slf4j-api:jar:1.7.3',
  :simple  => 'org.slf4j:slf4j-simple:jar:1.7.3')
PIPELINE    = struct(
  :api     => 'pipeline:pipeline-api:jar:2.1.1',
  :util    => 'pipeline:pipeline-util:jar:2.1.1')
LAMBDACALC  = 'lambdacalc:lambdacalc:jar:1.1.0'

task 'deploy-lexicon' => 'pelican:lexicon:deploy'
task 'render-lexicon' => 'pelican:lexicon:render'

# install dependencies
task 'install-deps' do
  from_git 'https://github.com/pepijnkokke/LambdaCalc'
  from_git 'https://github.com/pepijnkokke/pelican-api'
end

define 'pelican' do
  project.version = '1.2.0'

  eclipse.natures :java
  compile.options.source = '1.6'
  compile.options.target = '1.6'

  # implementation of pipeline-api
  define 'api' do
    compile.with LOMBOK,GUAVA,PIPELINE,LAMBDACALC,
      projects('lexicon','predcalc','settings')
    test.with JPARSEC,SNAKE_YAML,SLF4J,compile.dependencies
    test.exclude 'semante.flattener.rte.*'
    package :jar
  end

  # implementation of lexicon file parsing
  define 'lexicon' do
    compile.with LOMBOK,GUAVA,PIPELINE.api,LAMBDACALC,
      projects('settings')
    package :jar

    task :overwrite do
      cmd1 = "cp -f #{_(:src,:main,:resources,'default.lexicon')} #{SemAnTE["Lexicon"]["Default"]}"
      cmd2 = "cp -f #{_(:src,:main,:resources,'legacy.lexicon')} #{SemAnTE["Lexicon"]["Legacy"]}"
      puts cmd1
      puts cmd2
      system cmd1
      system cmd2
    end

    pdffile  = _(:target,'lexicon.pdf')
    lexicon  = _(:src,:main,:resources,'default.lexicon')
    preamble = _(:src,:main,:resources,'default.preamble')
    lex2tex  = _(:lex2tex,:dist,:build,:lex2tex,'lex2tex')

    file pdffile => [lexicon,preamble,lex2tex] do
      mkdir_p _(:target)
      texfile = _(:target,'lexicon.tex')
      puts render = "#{lex2tex} < #{lexicon} > #{texfile}"
      fail unless system render
      puts pandoc = "pandoc -N -H #{preamble} -o #{pdffile} #{texfile}"
      fail unless system pandoc
    end

    task :render => pdffile
  end

  # implementation of first order logic and smashing from hol to fol
  define 'predcalc' do
    compile.with LOMBOK,SLF4J,LAMBDACALC,projects('settings')
    test.with JPARSEC,GUAVA,SNAKE_YAML,SLF4J,compile.dependencies
    package :jar
  end

  # implementation of configuration files
  define 'settings' do
    compile.with LOMBOK,SNAKE_YAML
    package :jar
  end
end

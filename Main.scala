import better.files.File
import java.nio.file.Files
import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import org.scalasteward.core.application.Cli
import org.scalasteward.core.application.Context

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val tmp = Files.createTempDirectory("temp-steward")
    val a = Cli.Args(
      workspace = tmp,
      reposFile = File("repos.md"),
      defaultRepoConf = None,
      gitAuthorName = "aaa",
      gitAuthorEmail = "aaa@example.com",
      vcsLogin = "aaa",
      gitAskPass = File("aaa.sh"),
      envVar = Nil,
      disableSandbox = true,
      doNotFork = true
    )
    Context.step0[IO](a).use(_.stewardAlg.runF)
  }
}
